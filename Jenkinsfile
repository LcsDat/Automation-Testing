pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'  // Use 'bat' for Windows Jenkins
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Running automated tests...'
                bat 'mvn test'
            }
        }
    }
    
    post {
        success {
            echo 'Tests completed successfully! ✅'
        }
        failure {
            echo 'Tests failed! ❌'
        }
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
