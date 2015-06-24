$(document).ready( function () {
    var term = $("#term").val();
    $('#labelTable').dataTable({
        searching: false,
        processing: true,
        serverSide: true,
        sPaginationType: "full_numbers",
        sDom: "<'row'<'col-md-6'i><'col-md-6'<'pull-right'p>>r>t<'row'<'col-md-6'><'col-md-6'>>",
        oLanguage: {
            oPaginate: {
                sPrevious: "<",
                sNext: ">",
                sFirst: "<<",
                sLast: ">>"
            } ,
            sEmptyTable:"No results found. Please update your query.",
            sInfo: "Showing _START_ - _END_ of _TOTAL_ labels"
        },
        ajax: {
            url: "../search/searchJSON",
            data: {term:term},
            type: "POST"
        },
        columns: [
            {
                data: "labelDetails",
                "render" : function(data, type, r, meta) {
                    var content = '<a name="labelDetailsLink" href="'+data.id+'">'+data.title+'</a><p name="labelDescription">'+data.description+'</p>'
                    return content
                }
            }
        ],
        "fnDrawCallback": function(oSettings) {
            if ($('#labelTable tbody tr td').html() == "No results found. Please update your query.") {
                $('.dataTables_paginate').hide();
            }
        }
    });
} );