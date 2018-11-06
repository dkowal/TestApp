pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'mvn -B -DskipTests clean package', returnStatus: true)
      }
    }
    stage('Test') {
      post {
        always {
          junit 'target/surefire-reports/*.xml'

        }

      }
      steps {
        bat(script: 'mvn test', returnStatus: true)
      }
    }
  }
}