pipeline {
    agent any

    tools {
        // Must match your Jenkins Global Tool Configuration names exactly
        maven 'maven3'
        jdk 'jdk17' 
    }

    stages {
        stage('Pulling the repo') {
            steps {
                // Fixed: Added single quotes around branch and url
                git branch: 'main', url: 'https://github.com'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy Application') {
            steps {
                // Fixed: Groovy multiline string uses triple single-quotes ('''), not backticks (```)
                sh '''
                    pkill -f order-service || true
                    nohup java -jar target/order-service-1.0.0.jar > app.log 2>&1 &
                '''
            }
        }

        stage('Health Check') {
            steps {
                sleep 10
                sh 'curl -f http://localhost:8080/api/orders/health'
            }
        }
    }
}
