pipeline {
    agent any

    tools {
        // Must match your Jenkins Global Tool Configuration names exactly
        maven 'maven3'
        jdk 'jdk17' 
    }

    stages { // Fixed: Wrapped all stages inside a proper 'stages' block
        stage('Pulling the repo') {
            steps {
                // Fixed: Corrected the full repository URL path
                git branch: 'main', 
                    credentialsId: 'github-creds', 
                    url: 'https://github.com/Nhmanju/Order-service.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy Application') {
            steps {
                // Fixed: Added JENKINS_NODE_COOKIE to prevent Jenkins from killing background processes
                // Fixed: Explicitly redirect stdout and stderr to safely detach the process
                sh '''
                    export JENKINS_NODE_COOKIE=dontKillMe
                    pkill -f order-service || true
                    nohup java -jar target/order-service-1.0.0.jar > app.log 2>&1 &
                    sleep 2
                '''
            }
        }

        stage('Health Check') {
            steps {
                sleep 10
                sh 'curl -f http://localhost:8080/api/orders/health'
            }
        }
    } // Fixed: Properly closed the stages block
}
