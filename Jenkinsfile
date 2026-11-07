pipeline {
  agent any
  options { timestamps(); disableConcurrentBuilds() }
  stages {
    stage('Backend verify') { steps { sh 'mvn -B -f backend/pom.xml clean verify' } }
    stage('Frontend build') { steps { dir('frontend') { sh 'npm install'; sh 'npm run build' } } }
    stage('Validate Compose') { steps { sh 'docker compose config --quiet' } }
    stage('Build images on main') { when { branch 'main' } steps { sh 'docker compose build' } }
  }
  post { always { cleanWs() } }
}
