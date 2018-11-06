pipeline {
  agent any
  stages {
    
    stage('Build') {
      steps {
        bat(script: 'mvn -B -DskipTests clean package', returnStatus: true)
      }
    }
    
    stage('Test') {
      steps {
          junit 'target/surefire-reports/*.xml'
        }
      }
    
  }
  
}
