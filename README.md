# todo-clj-rest

App is really just two files at the moment project.clj and src/todo_rest/handler.clj

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    source .lein-env `containing DATABASE_URL`
    lein ring server

## Curls

```js
curl http://localhost:3000 -X GET -i
```

```js
curl http://localhost:3000 -X POST -i -d '{
    "todo": "first todo"
}' -H 'Content-type: application/json'
```

```js
curl http://localhost:3000 -X PUT -i -d '{
    "name": "second todo",
    "id": 1
}' -H 'Content-type: application/json'
```

```js
curl http://localhost:3000 -X DELETE -i -d '{
    "id": 22
}' -H 'Content-type: application/json'
```
## License

Copyright © 2017 FIXME
