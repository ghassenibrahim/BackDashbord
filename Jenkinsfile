pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }

    tools {
        jdk 'OpenJDK11'
    }

    environment {
        SLACK_CHANNEL = 'finance-application'
    }

    stages {
        stage('Frontend Build'){
            steps{
                dir('src/main/webapp/'){
                    sh 'npm install'
                    sh 'npm run buildTest'
                }
            }
        }
//        stage('Flyway clean - OPTIONAL'){
//            steps{
//                sh './gradlew flywayClean'
//            }
//        }
        stage('Backend Build'){
            steps{
                sh './gradlew clean'
                sh './gradlew build -PtestEnv --info'
                sh 'docker build -f Dockerfile -t finance-application-image .'
            }
        }
        stage('Stop application'){
            steps{
                sh 'docker rm -f finance-application-container || true'
            }
        }
        stage('Start application'){
            steps{
                sh 'docker run --restart always -p 8090:8090 --name finance-application-container -d finance-application-image'
            }
        }
    }
    post {
        always {
            slackNotify(env.SLACK_CHANNEL, currentBuild.result)
        }
    }
}

def slackNotify(String slackChannel, String buildResult) {
    if ( buildResult == "SUCCESS" ) {
        slackSend channel: slackChannel, color: "good", message: "Job: ${env.JOB_NAME} with build number ${env.BUILD_NUMBER} was successful. Job url: ${env.BUILD_URL}"
    }
    else if( buildResult == "FAILURE" ) {
        slackSend channel: slackChannel, color: "danger", message: "Job: ${env.JOB_NAME} with build number ${env.BUILD_NUMBER} was failed. Job url: ${env.BUILD_URL}"
    }
    else if( buildResult == "UNSTABLE" ) {
        slackSend channel: slackChannel, color: "warning", message: "Job: ${env.JOB_NAME} with build number ${env.BUILD_NUMBER} was unstable. Job url: ${env.BUILD_URL}"
    }
    else {
        slackSend channel: slackChannel, color: "danger", message: "Job: ${env.JOB_NAME} with build number ${env.BUILD_NUMBER} its resulat was unclear. Job url: ${env.BUILD_URL}"
    }
}
