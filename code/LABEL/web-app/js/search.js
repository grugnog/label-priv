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
                    var api = new $.fn.dataTable.Api( meta.settings );
                    var currentPage = api.page()+1 //If user is on first page 'api.page()' returns zero
                    var content = '<a name="labelDetailsLink" href="details?id='+data.id+'&title='+data.title+'&term='+term+'&page='+currentPage+'">'+data.title+'</a><p name="labelDescription">'+data.description+'</p>'
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