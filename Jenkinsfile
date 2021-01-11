pipeline{
  agent none
  stages{
   stage('pwd'){
        agent any
        steps{
          sh 'pwd'
        }
      }
    stage('docker build'){
      agent any
      steps{
        sh 'sudo docker build -t springio/gs-spring-boot-docker .'
      }
    }
    stage('docker run'){
      agent any
      steps{
        sh 'sudo docker run -p 8085:8085 springio/gs-spring-boot-docker'
      }
    }
  }
}
