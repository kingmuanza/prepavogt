/**
 *
 * @author 12Lions
 */


/*
 * ----Animation du logo de la page de connection au passage de la souris-------
 */
particlesJS.load('hero-wrapper', 'particles/particles.json', function () {});
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
    $('.ui.accordion').accordion();

    $('.section_muanza').hide();

    $('#section_accueil').show();


});

function gestionnaireSections(id, elem) {
    //id de la section à ouvrir
    $('.section_muanza').hide();
    $('.section_bouton').removeClass("active");
    console.log(elem);
    elem.classList.add("active");

    $(id).show();

}
/*----------------------------------------------------------------------------*/


        