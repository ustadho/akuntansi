/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('myModule', ['ui.bootstrap']);

var ModalDemoCtrl = function($scope, $modal, $log){
    $scope.items=['item1', 'item2', 'item3'];
    
    $scope.open = function(){
        var modalInstance = $modal.open({
           templateUrl: 'myModalContent.html',
           controller: ModalInstanceCtrl,
           resolve:{
               items: function(){
                   return $scope.items;
               }
           }
        });
        
        modalInstance.result.then(function(selectedItem){
           $scope.selected = selecteItem; 
        }, function(){
            $log.info('Modal dismissed at '+new Date());
        });
    }
};

//pleasee note that $modalInstance represents a modal window ( instance) dependency 
//It is not the same as the $modal service used above

var ModalInstanceCtrl = function($scope, $modalInstance, items){
    $scope.items=items;
    $scope.selected = {item: $scope.items[0]};
    
    $scope.ok = function(){
        $modalInstance.close($scope.selected.item);
    }
    $scope.cancel = function(){
        $modalInstance.dismiss('cancel');
    }
};
