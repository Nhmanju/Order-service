pipeline {
    agent any

    tools {
        // Must match your Jenkins Global Tool Configuration names exactly
        maven 'maven3'
        jdk 'jdk17' 
    }

           stage('Pulling the repo') {
            steps {
                // Fixed: Added your exact repository URL and the credential ID we created earlier
                git branch: 'main', 
                    credentialsId: 'github-creds', 
                    url: 'https://github.com'
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
