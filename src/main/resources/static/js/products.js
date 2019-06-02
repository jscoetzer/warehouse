var socket = new WebSocket('ws://localhost:8080/ws/products');

socket.addEventListener('message', function (event) {
    addProduct(JSON.parse(event.data));
});


var categoriesSelect = $("#selectCategories");
var supplierSelect = $("#selectSuppliers");
var productsDiv = $("#productsDiv");

$(categoriesSelect).change(refreshProducts);
$(supplierSelect).change(refreshProducts);


refreshProducts();



function handleMessage(eventData){
    var product = JSON.parse(eventData);
    var supplier = product.supplier.name;
    var category = product.category.name;
    var name = product.name;
}



function refreshProducts() {

    var categories = categoriesSelect.val();
    var supplier = supplierSelect.val();

    var url = "/products?";
    if (categories !== ""){
        url += "category=" + categories + "&";
    }

    if (supplier !== ""){
        url += "supplier=" + supplier;
    }
    console.log(url);

    $(".product").remove();

    $.ajax({
        url: url,
        async: true,
        method: "GET",
        success: function(products){
            var suppliers = [];
            var categories = [];

            products.forEach(function (item) {
                suppliers.push(item.supplier.name);
                categories.push(item.category.name);
                addProduct(item);
            });
            suppliers.sort().forEach(function(item){
                addToFilter(supplierSelect, item);
            });
            categories.sort().forEach(function(item){
                addToFilter(categoriesSelect, item);
            });
        }
    });
}

function addProduct(product){

    addToFilter(supplierSelect, product.supplier.name);
    addToFilter(categoriesSelect, product.category.name);

    var categories = categoriesSelect.val();
    var supplier = supplierSelect.val();

    if ((supplier === product.supplier.name || supplier === "") && (categories === product.category.name || categories === "")){
        var divTemplate = "<div class=\"row product\">" +
            "<div class=\"col-sm-4\"> <span >" + product.name + "</span> </div>" +
            "<div class=\"col-sm-4\"> <span >" + product.supplier.name + "</span> </div>" +
            "<div class=\"col-sm-4\"> <span >" + product.category.name + "</span> </div>" +
            "</div>";
        productsDiv.append(divTemplate);
    }
}

function addToFilter(select, value){
    if ($(select).find("option[value='" + value + "']").length === 0){
        $(select).append(new Option(value, value));
    }
}