FROM ubuntu:18.04

WORKDIR /tmp

RUN apt update && apt install -y curl

RUN curl -o multichain-2.0.3.tar.gz  https://www.multichain.com/download/multichain-2.0.3.tar.gz
RUN tar -xvzf multichain-2.0.3.tar.gz

WORKDIR /tmp/multichain-2.0.3
RUN mv multichaind multichain-cli multichain-util /usr/local/bin

RUN useradd -ms /bin/bash multichain
USER multichain

WORKDIR /home/multichain