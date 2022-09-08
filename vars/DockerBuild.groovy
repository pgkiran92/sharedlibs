def call(String hubUser, String project) {
    sh "docker build -t ${hubUser}/${project}:beta-${env.BUILD_NUMBER} ."
    withCredentials([usernamePassword(
            credentialsId: "dockerhub-cred",
            usernameVariable: "USER",
            passwordVariable: "PASS"
    )]) {
        sh "docker login -u '$USER' -p '$PASS'"
    }
    sh "docker image push ${hubUser}/${project}:beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
}
