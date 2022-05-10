#!/usr/bin/env python
# -*- coding: utf-8 -*-

import subprocess
import sys
import os

def build_maven():
    MAVEN_TOOL = ".{}mvnw".format(os.sep)
    MAVEN_CLEAN_LIFECYCLE = "clean"
    MAVEN_CLEAN_TARGET = "package"


    subprocess.call([MAVEN_TOOL, MAVEN_CLEAN_LIFECYCLE, MAVEN_CLEAN_TARGET]);

def handle_docker(docker_command):
    DOCKER_COMPOSE = "docker-compose"

    subprocess.call([DOCKER_COMPOSE, docker_command])


try:
    if "-h" in sys.argv or "--help" in sys.argv:
        print("[NO_ARGS] \t --- Builds and runs")
        print("-b or --build \t --- Builds the maven project and docker")
        print("-r or --run \t --- Just runs the docker containers")
    elif "-d" in sys.argv or "--build" in sys.argv:
        build_maven()
        handle_docker("build")
    elif "-r" in sys.argv or "--run" in sys.argv:
        handle_docker("up")
    else:
        build_maven()
        handle_docker("build")
        handle_docker("up")
except:
    sys.exit()