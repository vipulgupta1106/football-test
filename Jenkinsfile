pipeline{
  agent none
  stages{
    stage('Compile'){
      agent any
      steps{
        sh 'mvn compile'
      }
    }
    stage('Code Quality'){
      agent any
      steps{
        sh 'echo Sonarqube Code Quality Check Done'
      }
    }
    stage('Test'){
      agent any
      steps{
        sh 'mvn test'
      }
    }
    stage('Package'){
      agent any
      steps{
        sh 'mvn package'
      }
    }
    stage('Upload War File To Artifactory'){
      agent any
      steps{
        sh 'echo Uploaded War file to Artifactory'
      }
    }
  }
}