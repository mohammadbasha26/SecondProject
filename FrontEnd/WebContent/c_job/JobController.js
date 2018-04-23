app.controller("JobController", function($scope, $rootScope, $location, $http,
		$window) {
	console.log('-----starting Job controller')

	$scope.job = {
		'jobId' : 0,
		'jobDesignation' : '',
		'company' : '',
		'salary' : '',
		'location' : '',
		'jobDescription' : '',
		'createdDate' : ''
	};
	$scope.alljobdata;

	$scope.publishJob = function() {
		console.log('Adding Job Information');
		console.log($scope.job);
		$http.post('http://localhost:8085/CollaborationControllers/publishJob',
				$scope.job)

		.then(function(response) {
			console.log(response.status);
			console.log(response.data);

			console.log("before redirecting")
			$location.path('/home');

		})
	}

	function listJobs() {
		console.log('List Job Method');
		$http.get('http://localhost:8085/CollaborationControllers/showjobs')
				.then(function(response) {
					console.log(response.data);
					$scope.alljobdata = response.data;
				});

	}
	/*
	 * function listAllJobs() { console.log('list all job method');
	 * $http.get('http://localhost:8085/CollaborationControllers/showjobs')
	 * .then(function(response) { console.log(response.data);
	 * $scope.alljobdata=response.data; });
	 *  }
	 */

	listJobs();
	/* listAllJobs(); */

});