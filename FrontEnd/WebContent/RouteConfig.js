var app = angular.module('collaboration', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider, $locationProvider)

{
	$locationProvider.hashPrefix('');

	$routeProvider.when('/', {templateUrl : 'template/index.html'})
	.when('/login', {templateUrl : 'template/Login.html'})

	.when('/register', {templateUrl : 'template/Register.html'})
	.when('/contactUs', {templateUrl : 'template/ContactUs.html'})
	.when('/aboutUs', {templateUrl : 'template/AboutUs.html'})
	.when('/home', {templateUrl : 'c_user/home.html'})
	.when('/logout', {templateUrl : 'c_user/Logout.html'})
	.when('/blog', {templateUrl : 'c_blog/blog.html',
	'controller':'BlogController'	
	})
		.when('/myblog', {templateUrl : 'c_blog/MyApprovedBlogs.html',
			'controller':'BlogController'
			
		})
		.when('/allblog', {templateUrl : 'c_blog/AllUserBlog.html'})
		.when('/allapprovedblog', {templateUrl : 'c_blog/ShowAllApprovedBlogs.html'})
		.when('/showblog', {templateUrl : 'c_blog/ShowAllBlogs.html'})
		.when('/adminblog', {templateUrl : 'c_blog/AdminBlog.html'})
		
		.when('/job', {templateUrl : 'c_job/Job.html'})
		.when('/jobdetail', {templateUrl : 'c_job/JobDetail.html'})
	
});

app.run(function($rootScope,$cookies,$http)
{
	console.log('I am in run function');
console.log($rootScope.currentUser);

$rootScope.currentUser = $cookies.getObject('currentUser') || {};
if ($rootScope.currentUser) {
	$http.defaults.headers.common['Authorization'] = 'Basic'
			+ $rootScope.currentUser;
}
});