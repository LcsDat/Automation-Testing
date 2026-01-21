pipeline {
    agent any
    
    tools {
        maven 'Maven' // Adjust name to match your Jenkins Maven configuration
        jdk 'JDK-17' // Adjust to your JDK version
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        
        stage('Generate Report') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'extentV5',
                    reportFiles: 'Hasaki.html',
                    reportName: 'Test Report'
                ])
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Tests passed successfully!'
        }
        failure {
            echo 'Tests failed!'
        }
    }
}