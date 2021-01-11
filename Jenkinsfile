pipeline{
  agent none
  stages{
  stage('Cloning repo') {
        steps {
          git 'https://github.com/vipulgupta1106/football-test.git'
        }
      }
  stage('make directory'){
        agent any
        steps{
          sh 'mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)'
        }
      }

    stage('docker build'){
      agent any
      steps{
        sh 'docker build -t springio/gs-spring-boot-docker'
      }
    }
    stage('docker run'){
      agent any
      steps{
        sh 'docker run -p 8085:8085 springio/gs-spring-boot-docker'
      }
    }
  }
}