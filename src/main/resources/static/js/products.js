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

    $("#tableBody").empty();

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

        $("#tableBody").append("<tr>\n" +
            "<td>" + product.name + "</td>\n" +
            "<td>" + product.supplier.name + "</td>\n" +
            "<td>" + product.category.name + "</td>\n" +
            "</tr>")
    }
}

function addToFilter(select, value){
    if ($(select).find("option[value='" + value + "']").length === 0){
        $(select).append(new Option(value, value));
    }
}