package hr.alphacloud.server.service.mail;

import hr.alphacloud.server.model.command.account.CompanySaveCommand;
import hr.alphacloud.server.model.command.account.RegisterUserCommand;
import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Service
@AllArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private static final String SENDER_EMAIL_ADDRESS = "no-reply@alphacloud.hr";

    private static final String EMAIL_ACTIVATION = "html/activation";
    private static final String RESET_PASSWORD = "html/reset-password";
    private final JavaMailSender mailSender;
    private final TemplateEngine htmlTemplateEngine;

    @Override
    public void sendActivationEmail(CompanySaveCommand companySaveCommand, String password) throws MessagingException {
        Map<String, Object> args = new HashMap<>();
        args.put("name", String.format("%s %s", companySaveCommand.getAccount().getFirstName(), companySaveCommand.getAccount().getLastName()));
        args.put("company", companySaveCommand.getFullName());
        args.put("username", companySaveCommand.getAccount().getEmail());
        args.put("password", password);

        this.sendMail(
                companySaveCommand.getAccount().getEmail(),
                "AlphaCloud registration",
                EMAIL_ACTIVATION,
                Locale.ENGLISH,
                args);
    }

    @Override
    public void sendActivationEmail(RegisterUserCommand registerUserCommand, String password, Company company) throws MessagingException {
        Map<String, Object> args = new HashMap<>();
        args.put("name", String.format("%s %s", registerUserCommand.getFirstName(), registerUserCommand.getLastName()));
        args.put("company", company.getFullName());
        args.put("username", registerUserCommand.getEmail());
        args.put("password", password);

        this.sendMail(
                registerUserCommand.getEmail(),
                "AlphaCloud registration",
                EMAIL_ACTIVATION,
                Locale.ENGLISH,
                args);
    }

    @Override
    public void sendPasswordResetEmail(String token, User user) throws MessagingException {
        String url = "http://localhost:5432/" + "login/new-password/" + token + "/" + user.getEmail();

        Map<String, Object> args = new HashMap<>();
        args.put("name", String.format("%s %s", user.getFirstName(), user.getLastName()));
        args.put("username", user.getEmail());
        args.put("body", url);

        this.sendMail(
                user.getEmail(),
                "AlphaCloud registration",
                RESET_PASSWORD,
                Locale.ENGLISH,
                args);
    }

    private MimeMessageHelper prepareMail(String emailAddress, String subject, String templateName,
                                          Locale locale, Map<String, Object> args) throws MessagingException {
        final Context context = new Context(locale);
        context.setVariables(args);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        message.setSubject(subject);
        message.setFrom(SendMailServiceImpl.SENDER_EMAIL_ADDRESS);
        message.setTo(emailAddress);

//        ClassPathResource cpr = new ClassPathResource(templateName, this.getClass().getClassLoader());

        final String htmlContent = this.htmlTemplateEngine.process(templateName, context);
        message.setText(htmlContent, true);
        return message;
    }

    private void sendMail(String emailAddress, String subject, String templateName,
                          Locale locale, Map<String, Object> args) throws MessagingException {
        final MimeMessageHelper messageHelper = this.prepareMail(emailAddress, subject, templateName, locale, args);
        this.mailSender.send(messageHelper.getMimeMessage());
    }

}
