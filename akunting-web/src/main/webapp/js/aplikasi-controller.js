angular.module('akunting.controller', ['akunting.service'])
        .controller('LoginRedirectorController', ['$window', function($window) {
                $window.location = 'login.html';
            }])
        .controller('MenubarController', ['$http', '$scope', function($http, $scope) {
                $scope.userinfo = {};
                $http.get('homepage/userinfo').success(function(data) {
                    $scope.userinfo = data;
                });
            }])
        .controller('AboutController', ['$http', '$scope', function($http, $scope) {
                $scope.appinfo = {};
                $http.get('homepage/appinfo').success(function(data) {
                    $scope.appinfo = data;
                });
            }])
        .controller('ApplicationSessionsController', ['ApplicationSessionsService', '$scope', function(ApplicationSessionsService, $scope) {
                $scope.refresh = function() {
                    ApplicationSessionsService.list().success(function(data) {
                        $scope.sessioninfo = data;
                    });
                }

                $scope.refresh();
                $scope.kick = function(user) {
                    ApplicationSessionsService.kick(user).success($scope.refresh);
                };
            }])
        .controller('ApplicationConfigController', ['$scope', 'ApplicationConfigService', function($scope, ApplicationConfigService) {
                $scope.configs = ApplicationConfigService.query();
                $scope.edit = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    $scope.currentConfig = ApplicationConfigService.get({configId: x.id}, function(data) {
                        $scope.original = angular.copy(data);
                    });
                };
                $scope.baru = function() {
                    $scope.currentConfig = null;
                    $scope.original = null;
                }
                $scope.simpan = function() {
                    ApplicationConfigService.save($scope.currentConfig)
                            .success(function() {
                                $scope.configs = ApplicationConfigService.query();
                                $scope.baru();
                            });
                }
                $scope.remove = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    ApplicationConfigService.remove(x).success(function() {
                        $scope.configs = ApplicationConfigService.query();
                    });
                }
                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentConfig);
                }
                $scope.isConfigNameAvailable = function(value) {
                    if ($scope.currentConfig != null && $scope.currentConfig.id != null) {
                        return true;
                    }
                    for (var i = 0; i < $scope.configs.length; i++) {
                        var u = $scope.configs[i];
                        if (u.name === value) {
                            return false;
                        }
                    }
                    return true;
                }
            }])
        .controller('SystemMenuController', ['$scope', 'SystemMenuService', function($scope, SystemMenuService) {
                $scope.reloadMenupage = function(page) {
                    if (!page) {
                        page = 0;
                    }

                    $scope.menupage = SystemMenuService.query(page, function() {
                        $scope.pages = _.range(1, ($scope.menupage.totalPages + 1));
                    });
                }

                $scope.reloadMenupage();
                $scope.edit = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    $scope.currentMenu = SystemMenuService.get({id: x.id}, function(data) {
                        $scope.original = angular.copy(data);
                    });
                    $scope.parentSelection = _.filter($scope.menus, function(m) {
                        return m.id != x.id;
                    });
                };
                $scope.baru = function() {
                    $scope.currentMenu = null;
                    $scope.original = null;
                }
                $scope.simpan = function() {
                    SystemMenuService.save($scope.currentMenu)
                            .success(function() {
                                $scope.reloadMenupage();
                                $scope.baru();
                            });
                }
                $scope.remove = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    SystemMenuService.remove(x).success(function() {
                        $scope.reloadMenupage();
                    });
                }
                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentMenu);
                }
            }])
        .controller('PermissionController', ['$scope', 'PermissionService', function($scope, PermissionService) {
                $scope.permissions = PermissionService.query();
                $scope.edit = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    $scope.currentPermission = PermissionService.get({id: x.id}, function(data) {
                        $scope.original = angular.copy(data);
                    });
                };
                $scope.baru = function() {
                    $scope.currentPermission = null;
                    $scope.original = null;
                }
                $scope.simpan = function() {
                    PermissionService.save($scope.currentPermission)
                            .success(function() {
                                $scope.permissions = PermissionService.query();
                                $scope.baru();
                            });
                }
                $scope.remove = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    PermissionService.remove(x).success(function() {
                        $scope.permissions = PermissionService.query();
                    });
                }
                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentPermission);
                }
                $scope.isPermissionValueAvailable = function(value) {
                    if ($scope.currentPermission != null && $scope.currentPermission.id != null) {
                        return true;
                    }
                    for (var i = 0; i < $scope.permissions.length; i++) {
                        var u = $scope.permissions[i];
                        if (u.value === value) {
                            return false;
                        }
                    }
                    return true;
                }
            }])
        .controller('RoleController', ['$scope', 'RoleService', function($scope, RoleService) {
                $scope.roles = RoleService.query();
                $scope.unselectedPermission = [];
                $scope.unselectedMenu = [];
                $scope.selectedPermission = [];
                $scope.selectedMenu = [];
                $scope.edit = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    $scope.currentRole = RoleService.get({id: x.id}, function(data) {
                        $scope.original = angular.copy(data);
                    });
                    RoleService.unselectedPermission(x).success(function(data) {
                        $scope.unselectedPermission = data;
                    });
                    RoleService.unselectedMenu(x).success(function(data) {
                        $scope.unselectedMenu = data;
                    });
                };
                $scope.baru = function() {
                    $scope.currentRole = null;
                    $scope.original = null;
                }
                $scope.simpan = function() {
                    RoleService.save($scope.currentRole)
                            .success(function() {
                                $scope.roles = RoleService.query();
                                $scope.baru();
                            });
                }
                $scope.remove = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    RoleService.remove(x).success(function() {
                        $scope.roles = RoleService.query();
                    });
                }


                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentRole);
                }

                $scope.isRoleNameAvailable = function(value) {
                    if ($scope.currentRole != null && $scope.currentRole.id != null) {
                        return true;
                    }
                    for (var i = 0; i < $scope.roles.length; i++) {
                        var u = $scope.roles[i];
                        if (u.name === value) {
                            return false;
                        }
                    }
                    return true;
                }

                $scope.selectAllPermission = function($event) {
                    if ($event.target.checked) {
                        for (var i = 0; i < $scope.unselectedPermission.length; i++) {
                            var p = $scope.unselectedPermission[i];
                            if ($scope.selectedPermission.indexOf(p.id) < 0) {
                                $scope.selectedPermission.push(p.id);
                            }
                        }
                    } else {
                        $scope.selectedPermission = [];
                    }
                }

                $scope.updateSelectedPermission = function($event, id) {
                    var checkbox = $event.target;
                    if (checkbox.checked && $scope.selectedPermission.indexOf(id) < 0) {
                        $scope.selectedPermission.push(id);
                    } else {
                        $scope.selectedPermission.splice($scope.selectedPermission.indexOf(id), 1);
                    }
                }

                $scope.isPermissionSelected = function(id) {
                    return $scope.selectedPermission.indexOf(id) >= 0;
                }

                $scope.isAllPermissionSelected = function() {
                    return $scope.unselectedPermission.length === $scope.selectedPermission.length;
                }

                $scope.saveSelectedPermission = function() {
                    console.log($scope.selectedPermission);
                    for (var i = 0; i < $scope.selectedPermission.length; i++) {
                        var p = {id: $scope.selectedPermission[i]};
                        $scope.currentRole.permissionSet.push(p);
                    }
                    RoleService.save($scope.currentRole)
                            .success(function() {
                                RoleService.unselectedPermission($scope.currentRole)
                                        .success(function(data) {
                                            $scope.unselectedPermission = data;
                                            $scope.currentRole = RoleService.get({
                                                id: $scope.currentRole.id
                                            });
                                        });
                            });
                    $scope.showPermissionDialog = false;
                }

                $scope.cancelSelectedPermission = function() {
                    $scope.selectedPermission = [];
                    console.log($scope.selectedPermission);
                    $scope.showPermissionDialog = false;
                }

                $scope.removeSelectedPermission = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    var ixPermission = -1;
                    for (var i = 0; i < $scope.currentRole.permissionSet.length; i++) {
                        if (x.id === $scope.currentRole.permissionSet[i].id) {
                            ixPermission = i;
                            break;
                        }
                    }
                    if (ixPermission >= 0) {
                        $scope.currentRole.permissionSet.splice(ixPermission, 1);
                        RoleService.save($scope.currentRole)
                                .success(function() {
                                    RoleService.unselectedPermission($scope.currentRole)
                                            .success(function(data) {
                                                $scope.unselectedPermission = data;
                                                $scope.currentRole = RoleService.get({
                                                    id: $scope.currentRole.id
                                                });
                                            });
                                });
                    }
                }

                $scope.selectAllMenu = function($event) {
                    if ($event.target.checked) {
                        for (var i = 0; i < $scope.unselectedMenu.length; i++) {
                            var p = $scope.unselectedMenu[i];
                            if ($scope.selectedMenu.indexOf(p.id) < 0) {
                                $scope.selectedMenu.push(p.id);
                            }
                        }
                    } else {
                        $scope.selectedMenu = [];
                    }
                }

                $scope.updateSelectedMenu = function($event, id) {
                    var checkbox = $event.target;
                    if (checkbox.checked && $scope.selectedMenu.indexOf(id) < 0) {
                        $scope.selectedMenu.push(id);
                    } else {
                        $scope.selectedMenu.splice($scope.selectedMenu.indexOf(id), 1);
                    }
                }

                $scope.isMenuSelected = function(id) {
                    return $scope.selectedMenu.indexOf(id) >= 0;
                }

                $scope.isAllMenuSelected = function() {
                    return $scope.unselectedMenu.length === $scope.selectedMenu.length;
                }

                $scope.saveSelectedMenu = function() {
                    console.log($scope.selectedMenu);
                    for (var i = 0; i < $scope.selectedMenu.length; i++) {
                        var p = {id: $scope.selectedMenu[i]};
                        $scope.currentRole.menuSet.push(p);
                    }
                    RoleService.save($scope.currentRole)
                            .success(function() {
                                RoleService.unselectedMenu($scope.currentRole)
                                        .success(function(data) {
                                            $scope.unselectedMenu = data;
                                            $scope.currentRole = RoleService.get({
                                                id: $scope.currentRole.id
                                            });
                                        });
                            });
                    $scope.showMenuDialog = false;
                }

                $scope.cancelSelectedMenu = function() {
                    $scope.selectedMenu = [];
                    console.log($scope.selectedMenu);
                    $scope.showMenuDialog = false;
                }

                $scope.removeSelectedMenu = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    var ixMenu = -1;
                    for (var i = 0; i < $scope.currentRole.menuSet.length; i++) {
                        if (x.id === $scope.currentRole.menuSet[i].id) {
                            ixMenu = i;
                            break;
                        }
                    }
                    if (ixMenu >= 0) {
                        $scope.currentRole.menuSet.splice(ixMenu, 1);
                        RoleService.save($scope.currentRole)
                                .success(function() {
                                    RoleService.unselectedMenu($scope.currentRole)
                                            .success(function(data) {
                                                $scope.unselectedMenu = data;
                                                $scope.currentRole = RoleService.get({
                                                    id: $scope.currentRole.id
                                                });
                                            });
                                });
                    }
                }
            }])
        .controller('UserController', ['$scope', 'UserService', 'RoleService', function($scope, UserService, RoleService) {
                $scope.users = UserService.query();
                $scope.roles = RoleService.query();
                $scope.edit = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    $scope.currentUser = UserService.get({id: x.id}, function(data) {
                        $scope.original = angular.copy(data);
                    });
                };
                $scope.baru = function() {
                    $scope.currentUser = null;
                    $scope.original = null;
                }
                $scope.simpan = function() {
                    if ($scope.currentUser.active == null) {
                        $scope.currentUser.active = false;
                    }
                    UserService.save($scope.currentUser)
                            .success(function() {
                                $scope.users = UserService.query();
                                $scope.baru();
                            });
                }
                $scope.remove = function(x) {
                    if (x.id == null) {
                        return;
                    }
                    UserService.remove(x).success(function() {
                        $scope.users = UserService.query();
                    });
                }
                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentUser);
                }
                $scope.isUsernameAvailable = function(value) {
                    console.log('isUsernameAvailable', value);
                    if ($scope.currentUser != null && $scope.currentUser.id != null) {
                        return true;
                    }
                    for (var i = 0; i < $scope.users.length; i++) {
                        var u = $scope.users[i];
                        if (u.username === value) {
                            return false;
                        }
                    }
                    return true;
                }
            }])
        .controller('CoaController', ['$scope', '$modal', '$log', 'CoaService', 'AccTypeService', 'OfficeService', 'CurrencyService', '$window',
            function($scope, $modal, $log, CoaService, AccTypeService, OfficeService, CurrencyService, $window) {
                $scope.coa = CoaService.query(0, 1000);
                $scope.accTypeList = AccTypeService.query();
                $scope.officeList = OfficeService.query();
                $scope.currencyList = CurrencyService.query();
                $scope.search = "";
                $scope.oldSearch = "";
                $scope.currentPage = 1;
                $scope.totalItems;
                $scope.userinfo = {};
                CoaService.listAll().success(function(data) {
                    $scope.allCoa = data;
                });
//                $scope.test = function() {
//                    console('isAccNoAvailable :');
////                    if (value === '' || value===$scope.original.accNo) {
////                        return true;
////                    }
////                    for (var i = 0; i < $scope.allCoa.length; i++) {
////                        var u = $scope.allCoa[i];
////                        //console.log("AccNo: " + u.accNo);
////                        if (u.accNo === value && u.accNo != $scope.original.accNo) {
////                            return false;
////                        }
////                    }
//                    return 'true';
//                };
                
                
                
                $scope.reloadCoaPage = function() {
                    $scope.currentPage = $scope.search != $scope.oldSearch ? 1 : $scope.currentPage;
                    $scope.coaPage = CoaService.query($scope.search, $scope.currentPage, function() {
                        if ($scope.coaPage.totalElements !== $scope.totalItems) {
                            $scope.totalItems = $scope.coaPage.totalElements;
                        }
                    });
                };
                $scope.setCurrentPage = function(page) {
                    $scope.currentPage = page;
                };
                $scope.$watch('currentPage', $scope.reloadCoaPage, true);
                
                $scope.refresh = function() {
                    $scope.search = "";
                    $scope.currentPage = 1;
                    $scope.reloadCoaPage();
                };
                $scope.remove = function(x) {
                    if (x.accNo == null) {
                        return;
                    }
                    var yes = $window.confirm('Anda yakin untuk menghapus akun ini?');
                    if (yes) {
                        CoaService.remove(x).success(function() {
                            //$scope.coa = CoaService.query();
                            $scope.reloadCoaPage();
                        });
                    }
                };
                $scope.reloadCoaPage();

                $scope.baru = function() {
                    var modalInstance = $modal.open({
                        templateUrl: 'pages/templates/coa-form.html',
                        controller: 'CoaModalInstanceController',
                        resolve: {
                            allCoa: function(){
                                return $scope.allCoa;
                            },
                            coa: function() {
                                return null;
                            },
                            accTypeList: function() {
                                return $scope.accTypeList;
                            },
                            officeList: function() {
                                return $scope.officeList;
                            },
                            currencyList: function() {
                                return $scope.currencyList;
                            }
                        }
                    });
                    modalInstance.result.then(function(coa) {
                        $scope.coa = coa;
                    }, function() {
                        $log.info('Modal dismissed at: ' + new Date());
                        $scope.reloadCoaPage();
                    });
                };

                $scope.edit = function(x) {
                    var modalInstance = $modal.open({
                        templateUrl: 'pages/templates/coa-form.html',
                        controller: 'CoaModalInstanceController',
                        resolve: {
                            allCoa: function(){
                                return $scope.allCoa;
                            },
                            coa: function() {
                                return x;
                            },
                            accTypeList: function() {
                                return $scope.accTypeList;
                            },
                            officeList: function() {
                                return $scope.officeList;
                            },
                            currencyList: function() {
                                return $scope.currencyList;
                            }
                        }
                    });
                    modalInstance.result.then(function(coa) {
                        $scope.coa = coa;
                        console.log($scope.coa);
                    }, function() {
                        $log.info('Modal dismissed at: ' + new Date());
                        $scope.reloadPage();
                    });
                };
                
                $scope.isAccNoAvailable = function(value){
//                    if(value ==='' || ($scope.original !=null && value===$scope.original.accNo)){
//                        return true;
//                    }
//                    console.log('allCoa', $scope.allCoa);
//                    for(var i=0; i<$scope.allCoa.length; i++){
//                        var c=$scope.allCoa[i];
//                        if(c.accNo ===value ){
//                            return false;
//                        }
//                    }
                    return false;
                }
                
            }])
        .controller('CoaModalInstanceController', [
            '$http', '$scope', '$modalInstance', 'allCoa', 'coa', 'accTypeList', 'officeList', 'currencyList', 'CoaService',
            function($http, $scope, $modalInstance, allCoa, coa, accTypeList, officeList, currencyList, CoaService) {
                $scope.accTypeList = accTypeList;
                $scope.officeList = officeList;
                $scope.currencyList = currencyList;
                $scope.allCoa=allCoa;
                $scope.currentCoa = coa;
                $scope.original=coa;
                
                $http.get('homepage/userinfo').success(function(data) {
                    $scope.userinfo = data;
                });
                $scope.edit = function(x) {
                    if (x.accNo == null) {
                        return;
                    }
                    CoaService.get(x.accNo).success(function(data) {
                        $scope.original = angular.copy(data);
                        $scope.currentCoa = angular.copy(data);
                        console.log($scope.original);
                        $scope.oldAccNo = x.accNo;
                    });
                };
                var initForm=function(){
                    console.log('Masuk initForm');
                    if (!$scope.currentCoa) {
                        $scope.title = "Tambah CoA";
                        console.log('$scope.currentCoa === null');
                        $scope.original=null;
                        $scope.currentCoa={active: true};
                    } else {
                        
                        $scope.title = "Edit CoA";
                        $scope.edit(coa);
                    }

                };
                
                $scope.simpan = function() {

                    console.log('Simpan COA');
                    if($scope.currentCoa.active==null){
                        $scope.currentCoa.active=true;
                    }
                    if($scope.currentCoa.isBudget==null){
                        $scope.currentCoa.isBudget=true;
                    }
                    if($scope.currentCoa.user==null){
                        $scope.currentCoa.user={id: $scope.userinfo.id};
                    }
                    
                    console.log('currentCoa', $scope.currentCoa);
                    CoaService.save($scope.currentCoa, $scope.original==null? null: $scope.original.accNo).success(function() {
                        $modalInstance.close($scope.currentCoa);
                    });
                }

                $scope.cancel = function() {
                    $modalInstance.dismiss('cancel');
                };
                $scope.isClean = function() {
                    console.log('CurrentCoa', $scope.currentCoa);
                    return angular.equals($scope.original, $scope.currentCoa);
                }
                
                $scope.isAccNameAvailable = function(value) {
                    if (value === '' || value===$scope.original.accName) {
                        return true;
                    }
                    for (var i = 0; i < $scope.allCoa.length; i++) {
                        var u = $scope.allCoa[i];
                        if (u.accName === value && u.accName != $scope.original.accName) {
                            return false;
                        }
                    }
                    return true;
                };
                
                initForm();
            }])
        .controller('CoaTypeController', ['$scope', '$modal', '$log', 'AccTypeService', '$window',
            function($scope, $modal, $log, AccTypeService, $window) {
                $scope.unselectedType = [];
                $scope.modalTitle = "Tambah Jenis CoA";
                $scope.search = "";
                $scope.totalItems;
                $scope.oldTypeId = null;

                $scope.reloadPage = function() {
                    $scope.accTypeList = AccTypeService.query();
                };
                $scope.remove = function(x) {
                    if (x.typeId == null) {
                        return;
                    }
                    var yes = $window.confirm('Anda yakin untuk menghapus jenis akun ini?');
                    if (yes) {
                        AccTypeService.remove(x).success(function() {
                            $scope.reloadPage();
                        });
                    }
                };
                $scope.cancelSelectedType = function() {
                    $scope.selectedCoaType = [];
                    console.log($scope.selectedCoaType);
                    $scope.oldTypeId = null;
                };
                $scope.reloadPage();

                $scope.baru = function() {
                    var modalInstance = $modal.open({
                        templateUrl: 'pages/templates/coa-type-form.html',
                        controller: 'CoaTypeModalInstanceController',
                        resolve: {
                            coaType: function() {
                                return {};
                            },
                        }
                    });
                    modalInstance.result.then(function(type) {
                        $scope.coaType = type;
                        $scope.reloadPage();
                    }, function() {
                        $log.info('Modal dismissed at: ' + new Date())
                    });
                };

                $scope.edit = function(x) {

                    var modalInstance = $modal.open({
                        templateUrl: 'pages/templates/coa-type-form.html',
                        controller: 'CoaTypeModalInstanceController',
                        resolve: {
                            coaType: function() {
                                return x;
                            }
                        }
                    });
                    modalInstance.result.then(function(coaType) {
                        $scope.coaType = coaType;
                        console.log($scope.coa);
                    }, function() {
                        $scope.reloadPage();
                        $log.info('Modal dismissed at: ' + new Date());
                    });
                };

            }])
        .controller('CoaTypeModalInstanceController', [
            '$scope', '$modalInstance', 'coaType', 'AccTypeService',
            function($scope, $modalInstance, coaType, AccTypeService) {
                $scope.title = coaType.typeId === null ? "Tambah Jenis Coa" : "Edit Jenis CoA";
                $scope.coaType = coaType;
                $scope.allType = AccTypeService.query();

                $scope.edit = function(x) {
                    if (x.typeId == null) {
                        return;
                    }
                    $scope.oldTypeId = x.typeId;
                    $scope.currentType = AccTypeService.get({typeId: x.typeId}, function(data) {
                        $scope.original = angular.copy(data);
                        console.log($scope.original);
                    });
                };
                if ($scope.coaType.typeId === null) {
                    $scope.currentType = null;
                    $scope.original = null;
                    $scope.oldTypeId = null;
                } else {
                    $scope.edit($scope.coaType);
                }

                $scope.simpan = function() {
                    console.log($scope.currentType);
                    AccTypeService.save($scope.currentType, $scope.original==null? null: $scope.original.typeId)
                            .success(function() {
//                        $scope.coa = CoaService.query();
//                        $scope.reloadCoaPage();
//                        $scope.baru();
                        $modalInstance.close($scope.currentType);
                    });
                }

                $scope.cancel = function() {
                    $modalInstance.dismiss('cancel');
                };
                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentType);
                }
                $scope.isTypeIdAvailable = function(value) {
                    
                    if (value == '' || ($scope.original!=null && value==$scope.original.typeId)) {
                        return true;
                    }
                    for (var i = 0; i < $scope.allType.length; i++) {
                        var t = $scope.allType[i];
                        if (t.typeId === value) {
                            return false;
                        }
                    }
                    return true;
                }
                $scope.isAccNameAvailable = function(value) {
                    if (value === '' || ($scope.original!=null && value==$scope.original.typeName)) {
                        return true;
                    }
                    for (var i = 0; i < $scope.allType.length; i++) {
                        var u = $scope.allType[i];
                        if (u.typeName === value && u.typeName != $scope.original.typeName) {
                            return false;
                        }
                    }
                    return true;
                };
            }])
        .controller('JurnalController', ['$http', '$scope', '$location', 'JurnalService', 'CoaService', 'UserService',
            function($http, $scope, $location, JurnalService, CoaService, UserService) {
                // Datepicker directive

                $scope.modalTitle = "Tambah akun";
                $scope.showCoaDialog = false;
                $scope.jurnalDetail = [];
                $scope.selectedItems = [];
                $scope.tanggal = [];
                $scope.tanggal.mulai = new Date();
                $scope.tanggal.sampai = new Date();
                $http.get('homepage/userinfo').success(function(data) {
                    $scope.userinfo = data;
                    $scope.getUser = UserService.get({id: $scope.userinfo.id}, function(data) {
                        $scope.currentUser = angular.copy(data);
                        //console.log($scope.currentUser);
                    });
                });
                CoaService.listAll().success(function(data) {
                    $scope.allCoa = data;
                });
                $scope.reloadJurnalPage = function(page) {
                    if (!page) {
                        page = 0;
                    }
                    $scope.jurnalPage = JurnalService.query($scope.tanggal, page, function() {
                        $scope.pages = _.range(1, ($scope.jurnalPage.totalPages + 1));
                    });
                };
                $scope.reloadJurnalPage();
                $scope.baru = function() {
                    $scope.jurnalDetail = [];
                    $scope.original = null;
                    $scope.currentJurnal = null;
                    $scope.showCoaDialog = false;
                };
                $scope.cancel = function() {
                    $scope.showCoaDialog = false;
                };
                $scope.edit = function(x) {
                    if (x.id == null) {
                        return;
                    }

                    $scope.currentJurnal = JurnalService.get({id: x.id}, function(data) {
                        $scope.original = angular.copy(data);
                        //$routeProvider.otherwise({templateUrl: 'pages/transaksi/jurnal.html', controller: 'JurnalController'});
                        $location.path('/transaksi/jurnal');
                        $scope.currentJurnal = angular.copy(data);
                        console.log($scope.currentJurnal);
                    });
                };
                $scope.rowCollection = $scope.jurnalDetail;
                $scope.addItem2 = function(x) {
                    $scope.jurnalDetail.push({
                        akun: x, //{
                        //accName: x.accName,
                        //accNo: x.accNo,
                        //},
                        debet: 0,
                        kredit: 0
                    });
                    $scope.showCoaDialog = false;
                };
                $scope.columnCollection = [
                    {label: 'AccNo', map: 'akun.accNo'},
                    {label: 'Acc Name', map: 'akun.accName'},
                    {label: 'Debet', map: 'debet', formatFunction: 'currency', isEditable: true, type: 'number'},
                    {label: 'Kredit', map: 'kredit', formatFunction: 'currency', isEditable: true, type: 'number'},
                ];
                $scope.globalConfig = {
                    isPaginationEnabled: false,
                    itemsByPage: 1000,
                    maxSize: 8,
                    selectionMode: 'single',
                };
                $scope.tambahCoa = function() {
                    $scope.showCoaDialog = true;
                };
                $scope.cancelSelectedCoa = function() {
                    $scope.showCoaDialog = false;
                };
                $scope.isClean = function() {
                    return angular.equals($scope.original, $scope.currentJurnal) || $scope.jurnalDetail.length == 0
                            || ($scope.totalDebet == 0 || $scope.totalDebet != $scope.totalKredit);
                };
                $scope.simpan = function() {
                    if ($scope.currentJurnal.multiCurr == null) {
                        $scope.currentJurnal.multiCurr = false;
                    }
                    $scope.currentJurnal.listJurnal = $scope.jurnalDetail;
                    $scope.currentJurnal.user = $scope.currentUser;
                    JurnalService.save($scope.currentJurnal)
                            .success(function() {
                                //$scope.jurnal=JurnalService.query();
                                $scope.baru();
                            });
//            $scope.showPermissionDialog = false;
                };
                var calculateTotals = function() {
                    $scope.totalDebet = 0;
                    $scope.totalKredit = 0;
                    if ($scope.jurnalDetail == null) {
                        return;
                    }
                    for (var i = 0, len = $scope.jurnalDetail.length; i < len; i++) {
                        $scope.totalDebet += parseFloat($scope.jurnalDetail[i].debet);
                        $scope.totalKredit += parseFloat($scope.jurnalDetail[i].kredit);
                    }

                    //console.log($scope.jurnalDetail)
                };
                $scope.$watch('jurnalDetail', calculateTotals, true);
                $scope.gridOptions = {
                    data: 'jurnalDetail',
                    enableCellSelection: true,
                    enableRowSelection: false,
                    enableCellEditOnFocus: true,
                    multiSelect: false,
                    selectedItems: $scope.mySelections,
                    enableCellEdit: true,
                    columnDefs: [
                        {field: 'akun.accNo', displayName: 'Kode', enableCellEdit: false, width: 100},
                        {field: 'akun.accName', displayName: 'Keterangan', enableCellEdit: false, width: 300},
                        {field: 'debet', displayName: 'Debet', enableCellEdit: true, width: 100},
                        {field: 'kredit', displayName: 'Kredit', enableCellEdit: true, width: 100, cellFilter: 'numbers',
                            cellTemplate: '<div ng-class="{green: row.getProperty(col.field) > 30}"><div style="text-align:right;"  class="ngCellText">{{row.getProperty(col.field)}}</div></div>'},
                        {field: '', cellTemplate: '<a ng-click="remove(c)"><i class="icon-trash"></i> Delete </a>'}
                    ],
                };
            }])
        ;