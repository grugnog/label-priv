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
            url: "../textSearch",
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


   //To handle advance search
    $("#searchInput").autocomplete({
        autoFocus: true,
        source: function (request, response) {
            if(request.term.startsWith("#") && request.term.length > 1){
                var url = "${createLink(controller: 'search' , action:'autocomplete')}";
                $.ajax({
                    dataType:"JSON",
                    type:"GET",
                    data:{term:request.term},
                    url:"/LABEL/search/autocomplete",
                    success: function(data){
                        response(data);
                    },
                    error: function(data){
                        console.log("error.........");
                         //TODO:JanakiRam Show valid message
                    }
                });
            }
        },
        select: function(event, ui) {
            $("#searchInput").val("#"+ui.item.label+":");
            return false;
        }
    });
} );