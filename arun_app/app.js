var app = angular.module('myBlogApp',['ngRoute']);



app.config(function($routeProvider){
  $routeProvider.when('/',{
    templateUrl: 'public/views/about.html',
    controller: 'BlogController'
  });

//   .when('/about',{
//     templateUrl: 'public/views/about.html',
//     controller: 'AboutController',

// });
});
