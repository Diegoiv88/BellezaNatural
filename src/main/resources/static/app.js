const productsTable = document.querySelector("table#products-table");

document.addEventListener("DOMContentLoaded", () =>
{
    if(productsTable)
    {
        productsTable.addEventListener("click", productsTableHandler);
    }
});

function productsTableHandler(e)
{
    if(e.target.id === "btn-delete-product")
    {
       if(confirm("¿Estás seguro que desea realizar esta acción? Esta acción es irreversible!")) return 1;
       else e.preventDefault();
    }
}