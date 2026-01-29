pipeline {
    agent any

    stages {

        stage('Run Tests') {
            steps {
                echo 'Running automated tests...'
                echo 'Running automated tests1...'
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
            echo 'Archiving test reports...'
            echo 'Archiving test reports...!!!'
            echo 'Archiving test reports...!!!!!'
            //Archive the reports as artifacts
            archiveArtifacts artifacts: 'extentV5/**/*', allowEmptyArchive: true
            
            // Publish JUnit results if you have them
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
