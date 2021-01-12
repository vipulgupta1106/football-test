pipeline {
    agent any
    tools{
        maven "3.6.0"
    }
    stages {
        stage ('Maven install') {
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
            steps{
                sh 'docker stop webapp'
                sh 'docker system prune -f'
                sh 'docker build -t vipul/find-standings-project .'
            }
        }
        stage('docker run'){
            steps{
                 sh 'docker run --name webapp -d -p 8085:8085 vipul/find-standings-project'
            }
        }
    }
}