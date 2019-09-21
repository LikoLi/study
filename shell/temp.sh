#!/bin/bash

REF_NAME=$1

echo $REF_NAME

if [[ "$REF_NAME" =~ master|^[0-9]+\.[0-9]+$ ]]
then
    echo "IN"
else
    echo "No in"
fi
