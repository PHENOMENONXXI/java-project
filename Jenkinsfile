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
        maven 'Maven3'
    }
    
    environment {
        registry = "phenomenonxxi/jk-out-app"
        registryCredential = 'phenomenonxxi'
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
                sh 'mvn clean test'
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

                stage('Cloning our Git') {
                    steps {
                        git 'https://github.com/PHENOMENONXXI/java-project.git'
                    }
                }
                
                stage('Building the image') {
                    steps {
                        script {
                            dockerImage = docker.build registry
                        }
                    }
                }
                
                stage('Deploy the image') {
                    steps {
                        script {
                            docker.withRegistry('', registryCredential) {
                                dockerImage.push()
                            }
                        }
                    }
                }
                
//                 sh """
//                 echo "Deploying Code"
//                 """
            }
        }

    }   
}
