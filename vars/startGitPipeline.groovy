def call(Map pipelineParams) {
    pipeline {
        agent any
        stages {
            stage("checkout from github") {
                steps {
                    echo "HELLO WORLD FROM EXPRESSION STAGE"
                    git pipelineParams.gitURL
                }
            }

            stage("Run groovy script on DEV") {
                steps {
                    dir("DEV") {
                        //bat "groovy first_script.groovy"
                        //bat "groovy second_script.groovy"
                        bat "@echo off"
                        bat "for %%x in (*.groovy) do groovy %%x"
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
