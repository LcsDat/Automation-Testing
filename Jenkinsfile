pipeline {
    agent any
    
    stages {
        
        stage('Run Tests') {
            steps {
                echo 'Running automated tests...'
                bat 'mvn test'
            }
        }
        
        stage('Generate Report') {  // This should be inside 'stages' block
            steps {
                echo 'Publishing Extent Report...'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'extentV5',  // Just use the relative path, not variable concatenation
                    reportFiles: 'hideyashy.html',
                    reportName: 'Extent Test Report',
                    reportTitles: 'Automation Test Report'
                ])
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
            // Archive the reports as artifacts
            archiveArtifacts artifacts: 'extentV5/**/*', allowEmptyArchive: true
            
            // Publish JUnit results if you have them
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
