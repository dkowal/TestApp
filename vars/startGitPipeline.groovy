def call(def gitURL, def dir) {
    pipeline {
        agent any
        stages {
            stage("checkout from github") {
                steps {
                    echo "HELLO WORLD FROM EXPRESSION STAGE"
                    git gitURL
                }

            }

            stage("Run groovy script on DEV") {
                steps {
                    dir("DEV") {
                        bat "groovy first_script.groovy"
                    }
                    echo 'step app'
                }
            }

            stage("Run groovy script on TST") {
                steps {
                    input("Proceed on TST?")
                    dir("TST") {
                        bat "groovy first_script.groovy"
                    }
                }
            }

            stage("Calling library method") {
                steps {
                    sayHello "Dawid"
                    //input("Build second project?")
                    //build job: 'MavenProject_Test_01', parameters: [[$class: 'StringParameterValue', name: 'Parametr_01', value: "PIERWSZY PARAMETR"],
                    //                                                [$class: 'StringParameterValue', name: 'Parameter_02', value: "DRUGI PARAMETR"]]
                }
            }
        }
    }
}
