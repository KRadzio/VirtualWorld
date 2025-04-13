#include "Human.h"
#define UP 65
#define DOWN 66
#define RIGHT 67
#define LEFT 68
#define SPEC_ABILITY 'e'
#define PASS 'p'
#define EXIT 'k'
#define HUMAN_INFO_POS 8

Human::Human(World &wR, const int &xPos, const int &yPos)
    : Animal(wR, xPos, yPos, SYMBOL, STRENGTH, INITIATIVE, AllOrganisms::HUMAN) { this->typeS = "Czlowiek"; }
void Human::activateAbility()
{
    if (extraStrength == 0 && abilityCoolDown == 0)
    {
        this->extraStrength = 5;
        this->strength += extraStrength;
    }
}
void Human::handleCooldown()
{
    if (this->abilityCoolDown > 0)
        this->abilityCoolDown--;
}
void Human::handleAbility()
{
    if (this->extraStrength > 0)
    {
        if (this->extraStrength == 1)
            this->abilityCoolDown = 5;
        this->extraStrength--;
        this->strength--;
    }
}
void Human::takeAction()
{
    handleAbility();
    handleCooldown();
    printf("%c[%d;%df", 0x1B, HUMAN_INFO_POS, 1);
    cout << "Sila Czlowieka: " << this->strength << endl;
    if (this->abilityCoolDown > 0)
        cout << "Tury do ponownego uzycia umiejetnosci: " << this->abilityCoolDown << endl;
    char playerInput;
    bool finishedMove = false;
    int newY = this->yPos;
    int newX = this->xPos;
    while (finishedMove == false)
    {
        this->worldRef.clearEvents();
        playerInput = getchar();
        switch (playerInput)
        {
        case UP:
            if (this->worldRef.isPositionInsideWorld(newX, newY - 1))
            {
                newY--;
                finishedMove = true;
            }
            break;
        case DOWN:
            if (this->worldRef.isPositionInsideWorld(newX, newY + 1))
            {
                newY++;
                finishedMove = true;
            }
            break;
        case RIGHT:
            if (this->worldRef.isPositionInsideWorld(newX + 1, newY))
            {
                newX++;
                finishedMove = true;
            }
            break;
        case LEFT:
            if (this->worldRef.isPositionInsideWorld(newX - 1, newY))
            {
                newX--;
                finishedMove = true;
            }
            break;
        case PASS: // nie poruszamy się
            finishedMove = true;
            break;
        case SPEC_ABILITY:
            if (this->extraStrength == 0 && this->abilityCoolDown == 0)
                activateAbility();
            break;
        case EXIT:
            this->isAlive = false;
            finishedMove = true;
            break;
        default:
            break;
        }
    }
    if (this->worldRef.isPositionFree(newX, newY) != true)
        colision(this->worldRef.getOrganism(newX, newY));
    move(newX, newY);
}
Human::~Human() {}