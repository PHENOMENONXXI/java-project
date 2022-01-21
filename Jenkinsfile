pipeline {

    agent {
        node {
            label 'master'
        }
    }

    options {
        buildDiscarder logRotator( 
                    daysToKeepStr: '16', 
                    numToKeepStr: '10'
            )
    }
    
    tools {
        maven 'maven3'
    }
    
    environment {
        registry = "phenomenonxxi/jk-out-app"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }

    stages {
        
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }

        stage('Code Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM', 
                    branches: [[name: '*/main']], 
                    userRemoteConfigs: [[url: 'https://github.com/PHENOMENONXXI/java-project.git']]
                ])
            }
        }

        stage(' Unit Testing') {
            steps {
                sh 'mvn -B test --file pom.xml'
                sh 'mvn test'
            }
        }

        stage('Package cleaning') {
            steps {
                sh 'mvn clean package'
            }
        }
        
//         stage('Code Analysis') {
//             steps {
//                 sh """
//                 echo "Running Code Analysis"
//                 """
//             }
//         }

        stage('Build Deploy Code') {
//             when {
//                 branch 'develop'
//             }
            steps {
                sh """
                echo "Building Artifact"
                """
            }
        }
        
        stage('Cloning the Git') {
            steps {
//                 sh """
//                 echo "Cloning our Git"
//                 """
                sh "git clone 'https://github.com/PHENOMENONXXI/java-project.git'"
            }
        }
        
        stage('Building the image') {
            steps {
//                 sh """
//                 echo "Building the image"
//                 """
                script {
                    dockerImage = docker.build registry
                }
//                 sh "docker build . --file Dockerfile -t phenomenonxxi/jk-out-app"
            }
        }
        
        stage('Deploying the image') {
            steps {
//                 sh """
//                 echo "Deploy the image"
//                 """
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push("$BUILD_NUMBER")
                    }
                }
            }
        }
    }
}
                
//                 sh """
//                 echo "Deploying Code"
//                 """

