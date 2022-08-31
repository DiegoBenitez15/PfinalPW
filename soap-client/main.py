#!/usr/bin/env python
from zeep import Client
import json

def crearShortUrl(longUrl,usuario):
    jsonString = client.service.crearShortUrl(longUrl,usuario)

    if jsonString != "ERROR":
        obj = json.loads(jsonString)
        return obj

    return "ERROR: NO EXISTE EL USUARIO SOLICITADO"

def listarUrl(usuario,fecha):
    jsonString = client.service.getListadoURLEstadisticas(usuario,fecha)

    if jsonString != "ERROR":
        obj = json.loads(jsonString)
        return obj

    return "ERROR: NO EXISTE EL USUARIO SOLICITADO"

url = "http://localhost:7000/ws/UrlWebServices?wsdl"
client = Client(url)

#Creando URL a traves del Usuario admin
url_creado = crearShortUrl("https://onefootball.com/es/equipo/psg-263","admin")
print(url_creado)
url_creado = crearShortUrl("https://onefootball.com/es/equipo/inter-de-milan-16","admin")
print(url_creado)

#Creando URL con usuario no existente
url_creado = crearShortUrl("https://onefootball.com/es/equipo/psg-263","no_existo")
print(url_creado)

#Listando las URLs del usuario admin
urls = listarUrl("admin","2022-08-27")
print(urls)

#Listando las URLs con usuario no existente
urls = listarUrl("no_existo","2022-08-27")
print(urls)


