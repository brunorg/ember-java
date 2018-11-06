import Mirage from 'ember-cli-mirage';

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
      "id": "5bc6a8948e54e1d1ccf86b4b",
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
      "id": "4566a8948e54e1d1ccf86b4b",
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

  let generateGuid = function () {
    var result, i, j;
    result = '';
    for (j = 0; j < 24; j++) {
      if (j == 8 || j == 12 || j == 16 || j == 20) {
        result = result + '';
      }
      i = Math.floor(Math.random() * 16).toString(16).toUpperCase();
      result = result + i;
    }
    return result.toLowerCase();
  };

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
          "id": generateGuid(),
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

}
