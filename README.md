# rest-app
Esse projeto é uma simples api rest em clojure
Desenvolvimento com base no blog do medium:
https://medium.com/swlh/building-a-rest-api-in-clojure-3a1e1ae096e

# How to run
1.É necessário ter a ferramenta Leiningen instalada.
    sudo apt-get install leiningen

2.Iniciar o servidor execute:
    lein run

# Request
    #Exemplos 
    
    # Insere um novo usuário
    POST: curl -X POST http:/127.0.0.1:3000/add  --data "firstName=Usuario&lastName=UltimoNome"

    # Retorna a lista de usuários
    GET: curl http:/127.0.0.1:3000/list
## License

Copyright © 2020 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
