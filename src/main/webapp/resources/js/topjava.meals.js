const mealAjaxUrl = "profile/meals";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

function saveMeal(){
    const data = {
        // 1. "tag"
        // 2. ".class"
        // 3. "#id"
        dateTime: $("#dateTime").val(),
        description: $("#description").val(),
        calories: $("#calories").val()
    };

    $.ajax({
        type: "POST",
        url: mealAjaxUrl,
        data: JSON.stringify(data),
        // data: $("#mealData").serialize(),
        success: function (data) {                        //callback
            // $('#modalAddMeal').modal('hide');
            console.log("Saved meal successfully")
            window.location.replace(window.location.href);
        },
        dataType: 'json',
        contentType: 'application/json'
    });
}

function deleteMeal(id){
    $.ajax({
        type: "DELETE",
        url: mealAjaxUrl + "/" + id,
        success: function (){
            window.location.replace(window.location.href);
        }
    });
}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});