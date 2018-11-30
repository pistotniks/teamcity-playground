package AnotherProject.BuildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object RunBuild : BuildType({
    uuid = "d0398ad6-0fd0-4635-90a4-52a5dccbe0ad"
    name = "RunBuild"

    vcs {
        root(DslContext.settingsRoot)
        //checkoutMode = CheckoutMode.ON_SERVER
    }

    steps {
        script {
            name = "set version using script opa"
            scriptContent = """
                        #!/bin/bash
                        HASH=%build.vcs.number%
                        SHORT_HASH=${"$"}{HASH:0:7}
                        BUILD_COUNTER=%build.counter%
                        BUILD_NUMBER="1.0.${"$"}BUILD_COUNTER.${"$"}SHORT_HASH"
                        echo "##teamcity[buildNumber '${"$"}BUILD_NUMBER']"
                    """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }
})