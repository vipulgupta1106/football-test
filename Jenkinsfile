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
        sh 'docker build -t springio/gs-spring-boot-docker .'
      }
    }

  }
}