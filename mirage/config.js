import Mirage from 'ember-cli-mirage';
import { v1 as uuid } from 'uuid';

export default function () {

  // These comments are here to help you get started. Feel free to delete them.

  /*
    Config (with defaults).

    Note: these only affect routes defined *after* them!
  */

  this.urlPrefix = 'http://localhost:8080';    // make this `http://localhost:8080`, for example, if your API is on a different server
  this.namespace = '/api';    // make this `/api`, for example, if your API is namespaced
  this.timing = 400;      // delay for each request, automatically set to 0 during testing

  /*
    Shorthand cheatsheet:

    this.get('/posts');
    this.post('/posts');
    this.get('/posts/:id');
    this.put('/posts/:id'); // or this.patch
    this.del('/posts/:id');

    http://www.ember-cli-mirage.com/docs/v0.3.x/shorthands/
  */

  let customers = [
    {
      "type": "customers",
      "id": uuid(),
      "attributes": {
        "first-name": "John",
        "last-name": "Doe"
      },
      "relationships": {
        "orders": {
          "data": []
        }
      }
    },
    {
      "type": "customers",
      "id": uuid(),
      "attributes": {
        "first-name": "Frodo",
        "last-name": "Baggins"
      },
      "relationships": {
        "orders": {
          "data": []
        }
      }
    }
  ];

  let products = [
    {
      "type": "products",
      "id": uuid(),
      "attributes": {
        "name": "Sapiens",
        "description": "Sapiens: A Brief History of Humankind",
        "price": 26.84
      },
      "relationships": {
      }
    },
    {
      "type": "products",
      "id": uuid(),
      "attributes": {
        "name": "Pride and Prejudice",
        "description": "'Vanity, not love, has been my folly'",
        "price": 25.81
      },
      "relationships": {
      }
    },
    {
      "type": "products",
      "id": uuid(),
      "attributes": {
        "name": "1984",
        "description": "Nineteen Eighty-Four",
        "price": 29.90
      },
      "relationships": {
      }
    }
  ];

  /////////////
  // Customers
  /////////////

  this.get('/customers', function () {
    let filteredCustomers = customers.filter(function (i) {
      return i.attributes.deleted !== true;
    });
    return { "data": filteredCustomers }
  });

  this.get('/customers/:id', function (db, request) {
    if (request.id) {
      return {
        data: customers.find((customer) => request.params.id === customer.id)
      };
    } else{
      return new Mirage.Response(404);
    }

  });

  this.post('/customers', function (db, request) {
    let newCustomer = {
      data: {
        "type": "customers",
        "id": uuid(),
            "attributes": {
          "first-name": request.firstName,
            "last-name": request.lastName
        },
        "relationships": {
          "orders": {
            "data": []
          }
        }
      }
    }
    customers.push(newCustomer.data);
    return newCustomer;
  });

  this.delete('/customers/:id', function (db, request) {
    customers.find((customer) => request.params.id === customer.id).deleted = true;
    return { "data":  null};
  });

  /////////////
  // Products
  /////////////

  this.get('/products', function () {
    let filteredProducts = products.filter(function (i) {
      return i.attributes.deleted !== true;
    });
    return { "data": filteredProducts }
  });

  this.get('/products/:id', function (db, request) {
    if (request.id) {
      return {
        data: products.find((product) => request.params.id === product.id)
      };
    } else {
      return new Mirage.Response(404);
    }

  });

  this.post('/products', function (db, request) {
    let newProduct = {
      data: {
        "type": "products",
        "id": uuid(),
        "attributes": {
          "name": request.name,
          "description": request.description,
          "price": request.price
        },
        "relationships": {
        }
      }
    }
    products.push(newProduct.data);
    return newProduct;
  });

  this.delete('/products/:id', function (db, request) {
    products.find((product) => request.params.id === product.id).deleted = true;
    return { "data": null };
  });

}
