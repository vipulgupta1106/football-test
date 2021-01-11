pipeline{
  agent none
  stages{
    stage('docker build'){
      agent any
      steps{
        sh 'docker build -t springio/gs-spring-boot-docker'
      }
    }
    stage('docker run'){
      agent any
      steps{
        sh 'docker run -p 8080:8080 springio/gs-spring-boot-docker'
      }
    }
  }
}