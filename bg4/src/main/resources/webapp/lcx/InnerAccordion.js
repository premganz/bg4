/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function configureInternalAccordion()
{
    $(document).find('.unlinked').each(
        function()
        {
            var src = $(this).attr('src');
            var href = $(this).attr('href');
            
            if(src != undefined)
            {
                if(src.toString().indexOf(baseURL) == -1)
                {
                    src = baseURL+src;
                    $(this).attr('src', src);                    
                }
            }            
            else if (href !== undefined)
            {
                if(href.toString().indexOf(baseURL) == -1)
                {
                    href = baseURL+href;
                    $(this).attr('href', href);
                }
            }
            $(this).removeClass('unlinked');
        }
    );
    $('.inneraccordioncontent').hide();
    $('.inneraccordionheading').click(openAccordionContent);
}

function openAccordionContent(event)
{
    event.stopImmediatePropagation();
    var element = $(this);
    var parentId = element.parent().attr('id');
    var content = $('#'+parentId+' .inneraccordioncontent');

    if($(this).hasClass('currentSelection'))
    {
        content.hide(500);
        $(this).removeClass('currentSelection');
        $('.inneraccordionheading img').attr('src', baseURL+'links/images/arrowdown.gif');
        return;
    }
    $('.inneraccordionheading').removeClass('currentSelection');
    $(this).addClass('currentSelection');    

    $('.inneraccordionheading img').attr('src', baseURL+'links/images/arrowdown.gif');
    $(this).find('img').attr('src', baseURL+'links/images/arrowup.gif');

    $('.inneraccordioncontent').hide(500);
    content.show(500);
    
}
$(document).ready(configureInternalAccordion);