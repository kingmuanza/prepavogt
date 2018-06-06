/**
 *
 * @author 12Lions
 */


/*
 * ----Animation du logo de la page de connection au passage de la souris-------
 */
function bouger() {
    console.log("Animation logo activée");
    $('.image')
            .transition({
                debug: true,
                animation: 'jiggle',
                duration: 500,
                interval: 200
            })
            ;
}
/*----------------------------------------------------------------------------*/

/*
 *--------- Animation des sous-menus de menu de la page d'accueil---------------
 */
$(document).ready(function () {

    $("#chargement").fadeOut("fast");

    $('.section_muanza').css("display","none");

    $('#section_accueil').css("display","block");

    $('.section_muanza').on('click', function () {

    });


});

function gestionnaireSections(id, elem) {
    //id de la section à ouvrir
    $('.section_muanza').css("display","none");
    $('.section_bouton').removeClass("active");
    console.log(elem);
    elem.classList.add("active");
    $('.ui.accordion').accordion();
        

    $(id).css("display","block");

}

function ouvrirMenuCorrespondant(id, elemID, ref) {
    var elem = document.getElementById(elemID);
    gestionnaireSections(id, elem);
    var muanza = $('a[href*=' + ref + '].item:first');
    if (muanza !== null) {
        var les = $('div.ui.accordion');
        les.children().each(function () {
            this.classList.remove("active");
            console.log(this);// "this" is the current element in the loop
        });
        var kangudie = muanza.parent();
        
        kangudie.addClass(function(){
            
            return "active";
        });
        
    }
    $('.ui.accordion').accordion('refresh');
    //alert("muanza");
}



        