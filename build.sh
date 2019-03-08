#!/usr/bin/env bash
mvn clean install
if [ -d ~/Desktop/Server/plugins ];then
  cp target/terraform-minecraft-plugin-1.0.jar ~/Desktop/Server/plugins
fi

if [ -d ./spigot/plugins ];then
  cp target/terraform-minecraft-plugin-1.0.jar ./spigot/plugins
fi
