pipeline {
    agent any
    
    stages {
        
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
