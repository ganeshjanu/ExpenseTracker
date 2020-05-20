node {
    stage('GIT Checkout'){
        git branch: 'ganesh', credentialsId: 'git-cred', url: 'https://github.com/ganeshjanu/ExpenseTracker'
    
            }
    stage('MVN Build'){
        def mvnHome = tool name: 'Maven-3.6.3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} -f employee/pom.xml clean package surefire-report:report"     
        
    }
    stage('Build docker image'){
        sh 'docker build -f employee/Dockerfile -t friends-employee-docker.jfrog.io/employee-app:1.0.0 ./employee' 
	    //mail
        
    }
    stage('Push docker image'){
	withCredentials([usernamePassword(credentialsId: 'jprocred', passwordVariable: 'AFPWD', usernameVariable: 'AFUSN')]) {
     	sh "docker login -u ${AFUSN} -p ${AFPWD} friends-employee-docker.jfrog.io"
     	sh 'docker push friends-employee-docker.jfrog.io/employee-app:1.0.0'
	}
     
    }
      stage('remove docker image'){
    
 
        sh 'docker rmi -f friends-employee-docker.jfrog.io/employee-app:1.0.0'
    }
    
}
