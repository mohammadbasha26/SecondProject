var app = angular.module('collaboration', [ 'ngRoute']);
app.config(function($routeProvider, $locationProvider)

{
	$locationProvider.hashPrefix('');

	$routeProvider.when('/', {templateUrl : 'template/index.html'})
	.when('/login', {templateUrl : 'template/Login.html'})

	.when('/register', {templateUrl : 'template/Register.html'})
	
});