pipeline {
    agent any
    tools{
        maven "3.6.0"
    }

    stages {
        stage ('Maven Build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('docker build'){
                      agent any
                      steps{
                        sh 'docker build -t vipul/find-standings-project .'
                      }
                    }
    }
    post{
        always{
            cleanWs()
            }
        }
}