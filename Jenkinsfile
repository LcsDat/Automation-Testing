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

    stage('Generate Report') {
            steps {
                echo 'Publishing Extent Report...'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: "GlobalVariables.PROJECTPATH" + "/extentV5",  // Change this to your report directory
                    reportFiles: 'ExtentReport.html',         // Change this to your report filename
                    reportName: 'Extent Test Report',
                    reportTitles: 'Automation Test Report'
                ])
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
            archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
            
            // Publish JUnit results if you have them
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
