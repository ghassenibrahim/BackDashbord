package hr.alphacloud.server.service.mail;

import hr.alphacloud.server.model.command.account.CompanySaveCommand;
import hr.alphacloud.server.model.command.account.RegisterUserCommand;
import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;

import javax.mail.MessagingException;

public interface SendMailService {

    void sendActivationEmail(CompanySaveCommand registerUserCommand, String password) throws MessagingException;

    void sendActivationEmail(RegisterUserCommand registerUserCommand, String password, Company company) throws MessagingException;

    void sendPasswordResetEmail(String token, User user) throws MessagingException;
}
