$(document).ready( function () {
    var term = $("#term").val();
    $('#labelTable').dataTable({
        searching: false,
        processing: true,
        serverSide: true,
        "sDom": "<'row'<'col-md-6'i><'col-md-6'<'pull-right'p>>r>t<'row'<'col-md-6'><'col-md-6'>>",
        "oLanguage": {
            "oPaginate": {
                "sPrevious": "<",
                "sNext": ">"
            } ,
            "sEmptyTable":"No results found. Please update your query.",
            "sInfo": "Showing _START_ - _END_ of _TOTAL_ labels"
        },
        ajax: {
            url: '../search/searchJSON',
            data: {term:term},
            type: 'POST'
        },
        "columns": [
            { "data": "labelDetails" }
        ],
        "fnDrawCallback": function(oSettings) {
            if ($('#labels tbody tr td').html() == 'No labels found') {
                $('.dataTables_paginate').hide();
            }
        }
    });
} );