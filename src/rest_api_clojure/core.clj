(ns rest-api-clojure.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json])
  (:gen-class))


; Simple Body Page
(defn simple-body-page [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World"})


; Uma variavel Atom tem muitas propriedades,
; ele garante a mudança da variavel por diferentes threads, garantindo  
(def people-collection (atom []))

; Get the parameter specified by pname from :params object in req
(defn getparameter [req pname] (get (:params req) pname))

;Adiciona um usuário na lista
(defn addUser [firstname lastName]
  (swap! people-collection conj {
    :firstname (str/capitalize firstname) :lastName (str/capitalize lastName)
}))


(defn addUser-handler [req]
  {:status  200
    :headers {"Content-Type" "text/json"}
    :body    (-> (let [p (partial getparameter req)]
                  ; Adicionando valor no vector
                  (addUser (p :firstName) (p :lastName))
                  (str (json/write-str "Usuario adicionado com sucesso"))))})

; Return List of People
(defn get-list [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (str (json/write-str @people-collection))})

; Rotas
(defroutes app-routes
  (GET "/" [] simple-body-page)
  (GET "/list" [] get-list)
  (POST "/add" [] addUser-handler)
  (route/not-found "Error, page not found!"))

;Função main | Sobe o servidor e desabilita funções de sites, como por exemplo os cookies
(defn -main
  "This is our main entry point"
  [& args]
  ; Se não existir a variavel de ambiente referente a porta, como default se usa a porta 3000
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes (assoc-in site-defaults [:security :anti-forgery] false)) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))